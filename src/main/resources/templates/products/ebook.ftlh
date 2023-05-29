<#include "../parts/security.ftlh">
<#import "../parts/common.ftlh" as c>
<#import "../parts/messages.ftlh" as m>
<#import "../parts/images.ftlh" as i>
<@c.page>
    <@i.images book/>
    <table>
        <thead>
        <tr>
            <th>brand</th>
            <th>screenSize</th>
            <th>price</th>
            <th>year</th>

        </tr>
        </thead>
        <tbody>

            <tr>
                <td>${book.brand} </td>
                <td>${book.screenSize}</td>
                <td>${book.year}</td>
                <td>${book.price} </td>

              <#if isAdmin> <td><a href="/ebook/${book.id}/edit">edit</a></td></#if>
                <td><a href="/ebook/bucket/${book.id}/2">Add to bucket</a></td>
            </tr>

        </tbody>
    </table>
 <@m.messages listmessage redirectaddress!"/ebooks"/>
</@c.page>