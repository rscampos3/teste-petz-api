# teste-petz-api
Teste para Petz

<h1>Tutorial para cadastro de clientes e petshops, também a forma para consultar endereços</h1>
<br>
<p>Para acessar o link do swagger <a href="https://teste-petz-api.herokuapp.com/petz-api/swagger-ui.html" target="_blank">clique aqui</a>!</p>
<ul>
<li>
<h3>Autenticação</h3>
<p>Fazer autenticação no <b>/auth</b> utilizando usuário e senha enviado por email.
<br>
<b>Request</b><br>
{<br>
  &nbsp;"email": "string",<br>
  &nbsp;"senha": "string"<br>
}
</p>
</li>
<li>
<h3>Consultar Endereço</h3>
<p>
Antes de cadastrar o <b>cliente</b> ou <b>petshop</b> você deve consultar o endereço pelo cep no <b>/endereco/buscar-por-cep/{cep}</b> via <b>GET</b>, passando também o token de autenticação. 
</br>
<b>Response</b><br>
{<br>
  &nbsp;"id": 0,<br>
  &nbsp;"bairro": "string",<br>
  &nbsp;"cep": "string",<br>
  &nbsp;"cidade": "string",<br>
  &nbsp;"estado": "string",<br>
  &nbsp;"logradouro": "string"<br>
}
</p>
</li>
<li>
<h3>Cadastrar Cliente</h3>
<p>
Ao efetuar o cadastro de cliente pelo método <b>POST /cliente</b>, você deve passar o request abaixo utilizando o id do endereço antes consultado e token de autenticação.
<br><b>Request</b><br>
{<br>
  &nbsp;"complemento": "string",<br>
  &nbsp;"cpf": "string",<br>
  &nbsp;"email": "string",<br>
  &nbsp;<b>"idEndereco"</b>: 0,<br>
  &nbsp;"nomeCompleto": "string",<br>
  &nbsp;"numero": "string",<br>
  &nbsp;"referencia": "string",<br>
  &nbsp;"rg": "string",<br>
  &nbsp;"telefone": "string"<br>
}
</p>
</li>
<li>
<h3>Cadastrar PetShop</h3>
<p>
Ao efetuar o cadastro do petshop pelo método <b>POST /petshop</b>, você deve passar o request abaixo utilizando o id do endereço antes consultado e token de autenticação.
<br><b>Request</b><br>
{<br>
  &nbsp;"cnpj": "string",<br>
  &nbsp;"complemento": "string",<br>
  &nbsp;"email": "string",<br>
  &nbsp;<b>"idEndereco"</b>: 0,<br>
  &nbsp;"nomeFantasia": "string",<br>
  &nbsp;"numero": "string",<br>
  &nbsp;"razaoSocial": "string",<br>
  &nbsp;"referencia": "string",<br>
  &nbsp;"telefone": "string"<br>
}
</p>
</li>
</ul>
