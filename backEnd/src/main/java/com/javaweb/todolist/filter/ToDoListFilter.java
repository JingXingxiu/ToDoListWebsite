package com.javaweb.todolist.filter;

import com.javaweb.todolist.controller.dto.ErrorResponse;
import com.javaweb.todolist.controller.exception.filter.FilterInvalidTokenException;
import com.javaweb.todolist.controller.exception.filter.FilterNotExistUserException;
import com.javaweb.todolist.service.ToDoService;
import com.javaweb.todolist.service.UserService;
import com.javaweb.todolist.util.JacksonUtil;
import com.javaweb.todolist.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/api/todolist/*")
public class ToDoListFilter implements Filter {
    private final UserService userService=new UserService();
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        HttpServletResponse httpServletResponse=(HttpServletResponse) response;
        String token=httpServletRequest.getHeader("Authorization");
        try{
            if(token==null||!token.startsWith("Bearer ")) throw new FilterInvalidTokenException();
            String userID=JwtUtil.parseToken(token.substring(7).trim());
            if(!userService.findExistUser(userID)) throw new FilterNotExistUserException();
            httpServletRequest.setAttribute("userID",userID);
            chain.doFilter(request,response);
        }catch (FilterInvalidTokenException e){
            ErrorResponse errorResponse=new ErrorResponse(e.getModule(),e.getErrorMessage(),e.getStatus());
            JacksonUtil.writeJson(httpServletResponse,errorResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
