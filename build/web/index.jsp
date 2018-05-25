<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <style type="text/css">
        input, select, textarea {
            display: block;
            width: 200px;
        }

        section {
            margin: 20px auto;
        }
        
        
        .top-bar {
            margin: 20px auto;
        }
        
        
        .vcenter {
            display: flex;
            align-items: center;
        }
        
    </style>
    
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">    
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</head>
<body>

    
   

    <%
        System.out.println("hello world from index.jsp");
        Paciente paciente = (Paciente) request.getSession().getAttribute("paciente");
        MedicoDAO medicodao = new MedicoDAO();
        ConsultaDAO consultadao = new ConsultaDAO();

        if (paciente == null) {

            String msg = (String) request.getAttribute("msg");
            if (msg != null) { 
    %>

        <h4><%= msg%></h4>

    <%      
            } 
    %>

        <form action="/login" method="post">
            <input type="text" name="login" placeholder="Login"/>
            <input type="password" name="senha" placeholder="Senha"/>
            <input type="submit" value="Entrar"/>
        </form>

    <%
        } else {
    %>


    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand">Olá, <%= paciente.getNome()%></a>
        <form class="form-inline">
            <a class="btn btn-outline-primary my-2 my-sm-0" href="/logout">Sair</a>
        </form>
    </nav>
    
    <div class="container">


            
        <section>
            <h2>Cadastre sua consulta</h2>

            <form action="/cadastrar-consulta" method="post">
                <label>Especialidade</label>
                <select class="form-control" id="esp" name="especialidade">
                <% for (String esp : medicodao.getEspecialidades()) { %>
                    <option value="<%=esp%>"><%=esp%></option>
                <% } %>
                </select>

                <label>Médico</label>
                <select class="form-control" id="medico" name="medico_id">
                </select>

                <label>Convênio</label>
                <select class="form-control" name="convenio">
                    <option value="Amil">Amil</option>
                    <option value="Golden Cross">Golden Cross</option>
                    <option value="SulAmerica">SulAmerica</option>
                    <option value="AMS">AMS</option>
                    <option value="Porto Seguro">Porto Seguro</option>
                    <option value="Unimed">Unimed</option>
                </select>

                <label>Data</label>
                <input class="form-control" type="date" name="data"/>
                <label>Horario</label>
                <input class="form-control" type="time" name="horario"/>
                <input class="form-control" type="hidden" name="paciente_id" value="<%=paciente.getId()%>"/>

                <input class="btn btn-outline-primary" type="submit" value="Cadastrar"/>
            </form>
        </section>

        <section>
            <h2>Minhas Consultas</h2>

            <table border="1">
                <tr>
                    <th>Medico</th>
                    <th>Especialidade</th>
                    <th>Data</th>
                    <th>Horario</th>
                </tr>
                <%
                    for (Consulta consulta : consultadao.getConsultasPorPaciente(paciente)) { 
                        Medico medico = medicodao.getMedicoPorId(consulta.getMedicoId());
                %>
                <tr>
                    <td><%= medico.getNome() %></td>
                    <td><%= medico.getEspecialidade()%></td>
                    <td><%= consulta.getDataAsString("dd/MM/yyyy")%></td>
                    <td><%= consulta.getHorarioAsString("HH:mm").toString()%></td>
                </tr>
                <% } %>

            </table>
        </section>

    </div>

            
    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script>

        function getEspecialidades(){
            var especialidade = $(this).find(":selected").val();
            $.get("http://localhost:8080/listar-medicos", {especialidade: especialidade}, function(medicos){
                
                $("#medico").empty().append(
                    medicos.map(m => '<option value="' + m.id + '">' + m.nome + '</option>\n').join()
                );
            });
        }

        $("#esp").each(getEspecialidades);
        $("#esp").change(getEspecialidades);

    </script>

<%
    }
%>

    


</body>
</html>
