<#import "parts/common.ftlh" as c>
<@c.page>
    <table border="1">
        <thead>
        <tr>
            <th>order id</th>
            <th>product</th>
            <th>amount</th>
            <th>price</th>

        </tr>
        </thead>
        <tbody>
        <#list orderDetails as orderDetail>
            <tr>

                <td>${orderDetail.order.id}</td>
                <td><a href="/orderdetails/product/${orderDetail.product.id}">${orderDetail.product.title}</a> </td>
                <td>${orderDetail.amount}</td>
                <td>${orderDetail.price}</td>

            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>