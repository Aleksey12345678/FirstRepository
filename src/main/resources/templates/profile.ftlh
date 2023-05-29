<#import "parts/common.ftlh"as c>

<@c.page>
    <h5>${username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="mb-3"><label class="form-label"> Password:</label>
            <input type="password" class="form-control" name="password" placeholder="Password"/>
        </div>

        <div class="mb-3"><label class="form-label"> Email:</label>
            <input type="email" class="form-control" name="email" placeholder="Email" value="${email!''}"/>


        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>


</@c.page>