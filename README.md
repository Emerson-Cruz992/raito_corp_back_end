🚀 DOCUMENTAÇÃO COMPLETA – RAITÔ CORP API
Endpoints + Parâmetros + Bodies de Exemplo + Regras por Operação
📦 1. CATÁLOGO
⭐ 1.1 PRODUTOS
📌 POST /api/produtos/criar
Body (JSON)
{
  "nome": "Lâmpada LED 9W",
  "descricao": "Alta eficiência energética",
  "preco": 29.90,
  "ativo": true
}

Response
{
  "id": "UUID",
  "nome": "Lâmpada LED 9W",
  "descricao": "Alta eficiência energética",
  "preco": 29.9,
  "ativo": true,
  "criadoEm": "2025-11-16T..."
}

📌 GET /api/produtos

Sem parâmetros.

📌 GET /api/produtos/{idProduto}
📌 PUT /api/produtos/{idProduto}
Body parcial permitido:
{
  "nome": "Novo Nome",
  "preco": 45.00,
  "ativo": false
}

📌 DELETE /api/produtos/{idProduto}
⭐ 1.2 CATEGORIAS
📌 POST /api/categorias/criar
Body:
{
  "nome": "Luminárias",
  "descricao": "Produtos do tipo luminária"
}

📌 GET /api/categorias
⭐ 1.3 CARACTERÍSTICAS DE ILUMINAÇÃO
📌 POST /api/iluminacao/produto/{idProduto}
Parâmetros Query (todos obrigatórios, exceto os marcados)
Nome	Tipo	Obrigatório
potencia	String	✔
temperaturaCor	String	✔
fluxoLuminoso	String	✔
tensao	String	opcional
eficiencia	String	opcional
indiceProtecao	String	opcional
regulavel	boolean	default=false
Exemplo:
POST /api/iluminacao/produto/UUID?potencia=9W&temperaturaCor=3000K&fluxoLuminoso=900lm&tensao=Bivolt&eficiencia=90lm%2FW&indiceProtecao=IP20&regulavel=true

Response:
{
  "id": "UUID",
  "potencia": "9W",
  "temperaturaCor": "3000K",
  "fluxoLuminoso": "900lm",
  "tensao": "Bivolt",
  "eficiencia": "90lm/W",
  "indiceProtecao": "IP20",
  "regulavel": true
}

📌 GET /api/iluminacao/produto/{idProduto}
📌 PUT /api/iluminacao/{idCaracteristica}
Body parcial permitido:
{
  "tensao": "127V",
  "fluxoLuminoso": "1000lm",
  "regulavel": true
}

⭐ 1.4 IMAGENS
📌 POST /api/imagens/produto/{idProduto}/upload
Form-Data:
campo	tipo
imagem	file(.png/.jpg/.jpeg)
principal	boolean
📌 GET /api/imagens/produto/{idProduto}
📦 2. ESTOQUE
⭐ 2.1 Adicionar produto ao estoque
POST /api/estoque/adicionar?idProduto={UUID}&quantidade=10

⭐ 2.2 Atualizar quantidade
PUT /api/estoque/atualizar?idProduto={UUID}&quantidade=50

⭐ 2.3 Reservar
PUT /api/estoque/reservar?idProduto={UUID}&quantidade=3

⭐ 2.4 Liberar reserva
PUT /api/estoque/liberar?idProduto={UUID}&quantidade=2

⭐ 2.5 Movimentar saída
PUT /api/estoque/saida?idProduto={UUID}&quantidade=1

⭐ 2.6 Listar estoque
GET /api/estoque

⭐ 2.7 Buscar estoque por produto
GET /api/estoque/{idProduto}

📦 3. VENDAS
⭐ 3.1 CARRINHO
Criar carrinho
POST /api/carrinho/criar?idCliente={UUID}

Response:
{
 "idCarrinho": "UUID",
 "idCliente": "UUID",
 "itens": []
}

Listar itens do carrinho
GET /api/carrinho/{idCarrinho}/itens

Adicionar item
POST /api/carrinho/{idCarrinho}/adicionar?idProduto={UUID}&quantidade=2&preco=59.90

Remover item
DELETE /api/carrinho/{idCarrinho}/remover/{idProduto}

Limpar carrinho
DELETE /api/carrinho/{idCarrinho}/limpar

Calcular total
GET /api/carrinho/{idCarrinho}/total

⭐ 3.2 PEDIDOS
Finalizar pedido
POST /api/pedidos/finalizar?idCliente={UUID}&idCarrinho={UUID}&idEnderecoEntrega={UUID}

Response
{
  "idPedido": "UUID",
  "idCliente": "UUID",
  "valorTotal": 199.80,
  "status": "PENDENTE",
  "itens": [
    {
      "idProduto": "UUID",
      "quantidade": 2,
      "precoUnitario": 99.90
    }
  ]
}

Buscar pedido
GET /api/pedidos/{idPedido}

Listar pedidos do cliente
GET /api/pedidos/cliente/{idCliente}

Atualizar status
PUT /api/pedidos/{idPedido}/status?status=ENVIADO

📦 4. CADASTRO
⭐ 4.1 USUÁRIOS
Criar usuário
POST /api/usuarios/criar

Body:
{
  "nome": "Emerson",
  "sobrenome": "Araújo",
  "tipoUsuario": "cliente"
}

Listar usuários
GET /api/usuarios/listar

Buscar por ID
GET /api/usuarios/{id}

Deletar usuário
DELETE /api/usuarios/{id}

⭐ 4.2 CREDENCIAIS
Criar
POST /api/credenciais/criar

Body:
{
  "idUsuario": "UUID",
  "email": "teste@teste.com",
  "senhaHash": "123456"
}

Login
POST /api/credenciais/login?email=teste@teste.com&senha=123456

⭐ 4.3 CLIENTES
Criar cliente
POST /api/clientes/criar

Body:
{
  "idUsuario": "UUID",
  "cpf": "12345678901",
  "data_nascimento": "1999-05-10",
  "celular": "62999999999"
}

Buscar por CPF
GET /api/clientes/cpf/{cpf}

⭐ 4.4 ENDEREÇOS
Criar
POST /api/enderecos/criar

Body:
{
  "idCliente": "UUID",
  "cep": "74000000",
  "rua": "Av. Goiás",
  "numero": "120",
  "complemento": "Qd 05 Lt 10",
  "bairro": "Centro",
  "cidade": "Goiânia",
  "estado": "GO",
  "enderecoPrincipal": true
}

⭐ 4.5 PERFIS DE ACESSO
Criar
POST /api/perfis

Body:
{
  "nome": "ADMIN",
  "descricao": "Acesso total ao sistema"
}

⭐ 4.6 USUÁRIOS ⇄ PERFIS
Atribuir perfil
POST /api/usuarios-perfis/atribuir?idUsuario={UUID}&idPerfil={UUID}

Remover
DELETE /api/usuarios-perfis/remover?idUsuario={UUID}&idPerfil={UUID}

Listar perfis do usuário
GET /api/usuarios-perfis/listar-perfis-usuario?idUsuario={UUID}

Listar usuários de um perfil
GET /api/usuarios-perfis/listar-usuarios-perfil?idPerfil={UUID}
