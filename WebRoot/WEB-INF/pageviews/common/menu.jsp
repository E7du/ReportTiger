<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("cxt", request.getContextPath());
%>
<nav class='' id='main-nav'>
<div class='navigation'>
<div class='search'>
    <form accept-charset="UTF-8" action="search_results.html" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
        <div class='search-wrapper'>
            <input autocomplete="off" class="search-query" id="q" name="q" placeholder="Search..." type="text" value="" />
            <button class="btn btn-link icon-search" name="button" type="submit"></button>
        </div>
    </form>
</div>

<ul class='nav nav-stacked'>
<li class='active'>
    <a href='${cxt}'>
        <i class='icon-dashboard'></i>
        <span>Dashboard</span>
    </a>
</li>
<li>
    <a class='dropdown-collapse in' href='#'>
        <i class='icon-cogs'></i>
        <span>功能</span>
        <i class='icon-angle-down angle-down'></i>
    </a>
    <ul class='nav nav-stacked in'>
        <li class='<% if("1".equals( request.getParameter("active"))){ %>active<%}%>'>
            <a href='${cxt}/tiger'>
                <i class='icon-comments'></i>
                <span>添加App</span>
            </a>
        </li>
        <li class='<% if("2".equals( request.getParameter("active"))){ %>active<%}%>'>
            <a href='${cxt}/tiger/regCodeV'>
                <i class='icon-bar-chart'></i>
                <span>添加Code</span>
            </a>
        </li>
    </ul>
</li>
</ul>
</div>
</nav>
<section id='content'>