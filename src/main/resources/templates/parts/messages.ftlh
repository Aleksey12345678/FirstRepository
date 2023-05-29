<#macro messages listmessage redirectaddress>



    <div class="form-row">
        <form method="get" action=${redirectaddress} class="form-inline">
            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search">
            <button type="submit" class="btn btn-primary ml-2">Search</button>

        </form>
    </div>



    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid','')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" />
                    <#if textError??>
                    <div  class="invalid-feedback">
                        ${textError}
                    </div>
                    </#if>
                </div>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div>List messages</div>

    <#list listmessage as message>
        <div class="card my-3" style="width: 18rem;">
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
            </div>
            <div class="m2">
            <span>${message.text}</span>

            </div>
            <div class="card-footer">
               ${message.authorName}
            </div>
        </div>
    <#else>
        No message
    </#list>


</#macro>