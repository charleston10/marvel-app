
# Marvel App

Study application made to take advantage of the best programming practices using marvel's public api
Shows information such as series, characters, events and comics

- **Multilayer structure:**
Code organization inspired by DDD and Clean Architecture focused on codebase scalability.

- **Dependency injection:**
With Koin, a practical dependency injection library, the code will not be coupled and it'll still be easy to resolve automatically the dependencies on the runtime and mock them during the tests.

- **Coroutines:**
With coroutines it is possible to perform asynchronous tasks without changing the code flow of the application. Simplifies code by abstracting all the complexity of using threads

- **Kotlin KTS:**
Using Kotlin KTS we can take advantage of the application configuration using the kotlin language in our gradle file. This makes our configuration even easier

- **Databinding:**
Databinding is an easy way to control ui rules and makes it easier to bind data on the screen. This reduces large code boilerplate and leaves view rules centralized

## Layers

- **Presentation:**
Layer to manage initial data access through ViewModel

- **Domain:**
Layer to manage business rules using UseCase propagating events

- **Data:**
Layer for integrade access data using Repository and DataSource in local database or cloud

See more clean architecture [reference](https://www.linkedin.com/posts/charlestonanjos_clean-architecture-para-humanos-na-pr%C3%A1tica-activity-6589474515837833216-_dfe)

## Quick start

1. Create credentials of access api marvel in https://developer.marvel.com/ 
1. Clone the repository with `git clone --depth=1 https://github.com/charleston10/marvel-app`
2. Setup enviroment variable in your machine with your access the Marvel API with commands `set MARVEL_KEY_API_PUB=XXX`
`set MARVEL_KEY_API_PRI=XXX` this variables will be used to compile the project into the application's gradle file
4.  Run the application and be happy