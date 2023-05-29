<#macro images book>

    <#if book.productImages??>
        <#list book.productImages as image>
            <div class="card my-3" style="width: 18rem;">
                <div>
                    <#if image??>
                        <img src="/img/${image}" class="card-img-top" alt="image">
                    </#if>
                </div>
            </div>
        <#else>
            No images
        </#list></#if>


</#macro>

