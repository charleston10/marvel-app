
# Marvel App

Study application made to take advantage of the best programming practices using marvel's public api
Shows information such as series, characters, events and comics

[APK](https://github.com/charleston10/marvel-app/tree/master/apk/marvel_charleston.apk?raw=true) || [VIDEO](https://github.com/charleston10/github-android/blob/master/assets/videos/video-app.mp4?raw=true) || [YOUTUBE](https://youtu.be/T9XUBlfwAxk)

<table>
  <thead>
    <tr>
      <th>BASE</th>
      <th>Architecture</th>
      <th>IU</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>AppCompact</td>
      <td>DataBinding</td>
      <td>Navigation</td>
    </tr>
    <tr>
      <td>Android KTX</td>
      <td>Lifecycles</td>
      <td>Material Components</td>
    </tr>
     <tr>
      <td>Kotlin</td>
      <td>LiveData</td>
    </tr>
     <tr>
      <td>Android Arch</td>
      <td>ViewModel</td>
    </tr>
  </tbody>
</table>

**Screens**
<table>  
  <th>Home Light</th>
  <th>List Light</th>
  <th>Detail Light</th>
<tr>

<td>
   <img src="https://github.com/charleston10/marvel-app/blob/master/assets/images/main-light.PNG?raw=true"/>
  </td>
<td>
   <img src="https://github.com/charleston10/marvel-app/blob/master/assets/images/list-light.PNG?raw=true"/>
  </td>
   <td>
    <img src="https://github.com/charleston10/marvel-app/blob/master/assets/images/detail-light.PNG?raw=true"/>
  </td>
</tr>
</table>

<table>
  <th>Home Dark</th>
  <th>List Dark</th>
  <th>Detail Dark</th>
<tr>

<td>
   <img src="https://github.com/charleston10/marvel-app/blob/master/assets/images/main-dark.PNG?raw=true"/>
  </td>
<td>
   <img src="https://github.com/charleston10/marvel-app/blob/master/assets/images/list-night.PNG?raw=true"/>
  </td>
   <td>
    <img src="https://github.com/charleston10/marvel-app/blob/master/assets/images/detail-dark.PNG?raw=true"/>
  </td>
</tr>
</table>

## Base project

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

## CODE
- **IDE - Android Studio 4.0** 

- **Gradle 4.0.0**

- **Kotlin 1.3.72**

- **AAC Android Architecture Components** *using guide Google JetPack*

- **Clean Architecture** *for apply DRY, KISS, SOLID*

- **DataBinding** *bind data model with view*

- **ViewModel** *for interact view with business rules*

## API

Marvel Documentation: https://developer.marvel.com/


## DESIGN

**Material Components**

https://github.com/material-components

- Toolbar
- RecyclerView
- MaterialButton