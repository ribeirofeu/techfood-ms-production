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

## Relatório de Impacto à Proteção de Dados (RIPD)

Relatório de Impacto à Proteção de Dados Pessoais que visa descrever os processos de tratamento de dados pessoais que podem gerar riscos às liberdades civis e aos direitos fundamentais, bem como medidas, salvaguardas e mecanismos de mitigação de risco. O tratamento descrito neste documento envolve a coleta, armazenamento e processamento dos dados pessoais dos clientes. 

No documento corrente estão descritos os seguintes assuntos:
- Objetivo
- Identificação dos Agentes de Tratamento
- Necessidade de Elaborar o Relatório
- Descrição do Tratamento
- Necessidade e Proporcionalidade
- Identificação e Avaliação de Riscos

[Link do Relatório](https://drive.google.com/file/d/1-le6QO5-88kyiuXsPV5qOxdMIHSVSjcd/view?usp=drive_link)
