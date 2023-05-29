<#macro  login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="mb-3">
            <label class="form-label"> User Name : </label>
            <input type="text"
                   class="form-control  ${(usernameError??)?string('is-invalid','')}" name="username"
                   value="<#if user??>${user.username}</#if>"
                   placeholder="Enter username"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>

        <div class="mb-3"><label class="form-label "> Password:</label>
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}" name="password"
                   placeholder="Password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <#if isRegisterForm>
            <div class="mb-3">
                <label class="form-label"> Password:</label>
                <input type="password" class="form-control ${(password2Error??)?string('is-invalid','')}"
                       name="password2" placeholder="Retype password"/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
            <div class="mb-3"><label class="form-label "> Email:</label>
                <input type="email" class="form-control ${(emailError??)?string('is-invalid','')}" name="email"
                       placeholder="Email" value="<#if user??>${user.email}</#if>"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>

            </div>
            <div>
                <div class="g-recaptcha" data-sitekey="6Lc49rQjAAAAAMQ6tsII7766IMNCP-DLigyEJ2bt"></div>

                <#if captchaError??>
                    <div class="alert alert-danger" role="alert">
                        ${captchaError}
                    </div>
                </#if>
            </div>
        </#if>

        <#if !isRegisterForm ><a href="/registration">add new user</a></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary"><#if isRegisterForm> Create<#else>Sign In</#if></button>
    </form>

</#macro>

<#macro logout>

    <form action="/logout" method="post">
        <button type="submit" class="btn btn-primary">Sign Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
<#--    </div>-->

<#--    </form>-->
</#macro>