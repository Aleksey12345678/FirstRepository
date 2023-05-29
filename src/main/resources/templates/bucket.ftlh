<#import "parts/common.ftlh" as c>
<@c.page>
    <h2>Your bucket</h2>
    <h3>All sum-> <b >${bucket.sum}</b></h3>
    <table border="1">
        <thead>
        <tr>
            <th>Product</th>
            <th>Amount</th>
            <th>Price</th>
            <th>sum</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list bucket.bucketDetails as detail>
            <tr>
                <td><a href="/orderdetails/product/${detail.productId}">${detail.title}</a></td>
                <td>${detail.amount}</td>
                <td>${detail.price}</td>
                <td>${detail.sum}</td>
                <td><a href="/${detail.productId}/remove">Remove</a></td>
<#--               <a href="/user/${user.id}">edit</a>-->
            </tr>
        </#list>
        </tbody>
    </table>
    <a class="btn btn-primary" href="/toOrder" role="button">Добавить</a>


</@c.page>