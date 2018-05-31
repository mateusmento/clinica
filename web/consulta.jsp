<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.*"%>

<%
    Paciente paciente = (Paciente) request.getSession().getAttribute("paciente");
    MedicoDAO medicodao = new MedicoDAO();
    ConsultaDAO consultadao = new ConsultaDAO();
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
            <div class="form-group">
                <label>Especialidade</label>
                <select class="form-control" id="esp" name="especialidade" required>
                <% for (String esp : medicodao.getEspecialidades()) { %>
                    <option value="<%=esp%>"><%=esp%></option>
                <% } %>
                </select>

                <label>Médico</label>
                <select class="form-control" id="medico" name="medico_id" required>
                </select>

                <label>Convênio</label>
                <select class="form-control" name="convenio" required>
                    <option value="Amil">Amil</option>
                    <option value="Golden Cross">Golden Cross</option>
                    <option value="SulAmerica">SulAmerica</option>
                    <option value="AMS">AMS</option>
                    <option value="Porto Seguro">Porto Seguro</option>
                    <option value="Unimed">Unimed</option>
                </select>

                <label>Data</label>
                <input class="form-control" type="date" name="data" required/>
                <label>Horario</label>
                <input class="form-control" type="time" name="horario" required/>
                <input class="form-control" type="hidden" name="paciente_id" value="<%=paciente.getId()%>"/>
            </div>
            <input class="btn btn-outline-primary" type="submit" value="Cadastrar"/>
        </form>
    </section>

    <section>
        <h2>Minhas Consultas</h2>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Especialidade</th>
                    <th scope="col">Medico</th>
                    <th scope="col">Convenio</th>
                    <th scope="col">Data</th>
                    <th scope="col">Horario</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Consulta consulta : consultadao.getConsultasPorPaciente(paciente)) { 
                        Medico medico = medicodao.getMedicoPorId(consulta.getMedicoId());
                %>
                <tr scope="row">
                    <td><%= medico.getEspecialidade()%></td>
                    <td><%= medico.getNome() %></td>
                    <td><%= consulta.getConvenio()%></td>
                    <td><%= consulta.getDataAsString("dd/MM/yyyy")%></td>
                    <td><%= consulta.getHorarioAsString("HH:mm").toString()%></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </section>

</div>

            
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
