<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>
<#import "../parts/messages.ftlh" as m>
<#import "../parts/images.ftlh" as i>
<@c.page>
    <@i.images book/>
    <table>
        <thead>
        <tr>
            <th>Author</th>
            <th>Book Title</th>
            <th>Year of publication</th>
            <th>Price</th>

        </tr>
        </thead>
        <tbody>

            <tr>
                <td>${book.author} </td>
                <td>${book.bookTitle}</td>
                <td>${book.year} </td>
                <td>${book.price}</td>
             <#if isAdmin> <td><a href="/book/${book.id}/edit">edit</a></td></#if>
                <td><a href="/book/bucket/${book.id}/2">Add to bucket</a></td>
            </tr>

        </tbody>
    </table>
    <@m.messages listmessage redirectaddress!"/books"/>
</@c.page>