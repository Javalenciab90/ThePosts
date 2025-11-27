# THE POSTS

Aplicación Multimodular hecha en Compose con clean architecture y patrón de presentación MVI.

Funcionamiento: Visualizar los POSTS obtenidos de la página https://jsonplaceholder.typicode.com/posts
con la posibilidad de agregar comentarios sobre el Post seleccionado.

## Video

https://github.com/user-attachments/assets/da2c0424-3eba-44f8-b2c3-cd490272605a


## Esta App esta estructurada de la siguiente forma:

**- app:** Aloja la activity principal y el NavigationHost para la navegación.

**- design-system:** Contiene componentes abstractos y el tema de la aplicación, definiendo la consistencia de la interfaz de usuario.

**- platform:** Maneja las clases base para ViewModel, CoroutineContext, and Maneja los effects de UI. Además define las rutas de navegación.

**- domain:** Maneja los modelos de dominio. Los mapas (DataMapper interface) generico.

**- features:** Contiene las funcionalidades, para este caso la visualizaciñon de todos los POSTS con un buscador. Y Visualisar y Agrear comentarios sobre un POST seleccionado

**- data module:** Maneja los Datasource (API, ROOM). Obtiene la data a través de la API y realiza un cache mediante ROOM, siendo esta última la unica fuente de Datos (Room).

### Contiene Unit Tests para los ViewModels.

### Principal Libraries

- [Retrofit2](https://github.com/square/retrofit)
- [HILT](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)
- [Compose](https://developer.android.com/develop/ui/compose/bom?hl=es-419)
- [Room](https://developer.android.com/jetpack/androidx/releases/room?hl=es-419)

