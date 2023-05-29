<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>
<#import "../parts/images.ftlh" as i>
<@c.page>
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Product</th>
            <th>Price</th>
            <#if isAdmin>
                <th></th></#if>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list books as book>
            <tr>
                <td><@i.images book/></td>
                <td><a href="/ebook/${book.id}">${book.title}</a></td>
                <td>${book.price}</td>
                <#if isAdmin>
                    <td><a href="/ebook/${book.id}/edit">edit</a></td></#if>
                <td><a href="/ebook/bucket/${book.id}/1">Add to bucket</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#if isAdmin><a href="/ebook/new">add product</a></#if>
</@c.page>