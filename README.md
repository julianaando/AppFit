## Projeto AppFit 🏋️

---
#### ⏳ Desenvolvido durante os módulos Programação Web II e Testes Automatizados I no curso Back-end Java da [Ada](https://ada.tech/)

---
### ⚙️ Como executar:
Clone o repositório em uma pasta de preferência
```
git@github.com:julianaando/AppFit.git
```
Entre na pasta que você acabou de clonar, e instale as dependências
```
mvn install
```
Suba o container do docker
```
docker-compose up
```
Rode o projeto para verificar ...

TODO: pode testar com o POSTMAN

### 📝 Proposta:
Criação de uma API REST que simula um aplicativo de controle de atividades físicas, filtradas por data e tipos de exercícios.<br>
Um endpoint CRUD para o usuário será usado no login da aplicação.<br>
Endpoints de buscas que recebem filtros opcionais realizam consultas na camada de dados.<br>

### :woman_technologist: Testes Automatizados
Testes unitários para a parte lógica.<br>
Testes de integração para repositórios e web.<br>
Teste [end-to-end](https://github.com/julianaando/Appfit-tests) através da ferramenta Cucumber.<br>

----
### 💡 Conhecimentos aplicados:

- Spring Boot;
- JWT Web Token;
- Spring Data;
- Spring Security;
- Banco de Dados H2 e PostgreSQL;
- Hibernate
- JUnit
- Cucumber
- JaCoCo
---
