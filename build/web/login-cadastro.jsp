<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-light bg-light">
    <button class="btn btn-primary" data-toggle="modal" data-target="#login">Login</button>
    <button class="btn btn-primary" data-toggle="modal" data-target="#cadastrar">Cadastrar</button>
</nav>

<% String msg = (String) request.getSession().getAttribute("msg"); %>
<% if (msg != null) { %>
    <div class="container">
        <section>
            <h4><%= msg%></h4>
        </section>
    </div>
    <% request.getSession().setAttribute("msg", null); %>
<% } %>


<div class="modal" id="login">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">Faça seu login</h2>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/login" method="post">
                    <div class="form-group">
                        <label>Login </label>
                        <input class="form-control" type="text" name="login" required/>
                        <label>Senha </label>
                        <input class="form-control" type="password" name="senha" required/>
                    </div>
                    <input class="btn btn-primary" type="submit" value="Entrar"/>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="modal active" id="cadastrar">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">Crie uma conta</h2>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/cadastrar-paciente" method="post">
                    <div class="form-group">
                        <label>Nome </label>
                        <input class="form-control" type="text" name="nome" required/>
                        <label>Login </label>
                        <input class="form-control" type="text" name="login" required/>
                        <label>Senha </label>
                        <input class="form-control" type="password" name="senha" required/>                        
                    </div>
                    <input class="btn btn-primary" type="submit" value="Entrar"/>
                </form>
            </div>
        </div>
    </div>
</div>
