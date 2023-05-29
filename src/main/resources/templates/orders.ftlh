<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <table border="1">
        <thead>
        <tr>
            <th>id</th>
            <th>created</th>
            <th>updated</th>
            <th>user</th>
            <th>sum</th>
            <th>address</th>
            <th>status</th>
            <th></th>
            <th></th>
           <#if isAdmin><th></th></#if>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
<#--                <td><a href="/products/product/${product.id}">${product.title}</a> </td>-->
                <td>${order.id}</td>
                <td>${order.created}</td>
                <td>${order.updated}</td>
                <td>${order.user.username}</td>
                <td>${order.sum}</td>
                <td>${order.address}</td>
                <td>${order.status}</td>
                <td><a href="/orderdetails/${order.id}">orderDetail</a></td>
                <td><a href="/orders/${order.id}/edit/canceled">cancel</a></td>
               <#if isAdmin> <td><a href="/orders/${order.id}/edit/closed">closed</a></td></#if>

<#--                <td><a href="/products/${product.id}/bucket/1">Add to bucket</a></td>-->
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>