<#import "../parts/common.ftlh"as c>
<#import "../parts/images.ftlh" as i>
<@c.page>
    <h5><#if edit??>edit book<#else>add book</#if></h5>
    <#if edit??>  <@i.images book/></#if>
    <form action="<#if edit??>/ebook/${book.id}/edit<#else>/ebook/new</#if>" method="post" enctype="multipart/form-data">

        <div class="mb-3"><label class="form-label"> brand:</label>
            <input type="text" class="form-control" value="<#if book??>${book.brand}</#if>" name="brand"
                   placeholder="brand"/>
        </div>
        <div class="mb-3"><label class="form-label"> screen size:</label>
            <input type="text" class="form-control" value="<#if book??>${book.screenSize}</#if>" name="screenSize"
                   placeholder="screen size"/>
        </div>
        <div class="mb-3"><label class="form-label"> Price:</label>
            <input type="text" class="form-control" value="<#if book??>${book.price}</#if>" name="price"
                   placeholder="price"/>
        </div>
        <div class="mb-3"><label class="form-label"> Year:</label>
            <input type="text" class="form-control" value="<#if book??>${book.year}</#if>" name="year"
                   placeholder="year"/>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="productfile" id="bookFile">
                <label class="custom-file-label" for="bookFile">Choose file</label>
            </div>
        </div>



        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>


</@c.page>