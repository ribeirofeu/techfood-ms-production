## OWASP Zap Scan

Com o intuito de identificar as vulnerabilidades de segurança da nossa aplicação, nós utilizamos o OWASP Zap, uma ferramenta de segurança de aplicativos web de código aberto que entre as principais características estão a interceptação e modificação de tráfego HTTP/HTTPS, testes automatizados de segurança, e a capacidade de executar ataques ativos e passivos contra aplicações web.

Interceptamos os seguintes fluxos dos seguintes serviços:

### Serviço de pedido (order)
- Listar/exibir cardápio. [GET] /products
- Realização do pedido (Checkout). [POST] /orders

### Serviço de pagamento (payment)
- Geração do pagamento. /payment/generate
- Confirmação de pagamento (Webhook). /payment/webook

Link do drive contendo o .zip com o report e imagens do Zap mostrando o Scan e as rotas. Nenhuma vulnerabilidade alta foi encontrada antes, então não foi necessária nenhuma alteração de código.

https://drive.google.com/drive/folders/1ZbqH0qZuAqJelfGKluSGw59cUEAHhmOc?usp=sharing
