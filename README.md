# BoletaMaster

Sistema de gestión de boletería para eventos desarrollado en Java. Permite crear y administrar eventos, vender tiquetes, gestionar paquetes, procesar reembolsos y operar un marketplace de reventa entre usuarios.

Este repositorio corresponde al **Proyecto 3**, la tercera entrega del sistema dentro del curso de Diseño y Patrones de Objetos.

## Contenido

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Cómo ejecutar](#cómo-ejecutar)
- [Usuarios de prueba](#usuarios-de-prueba)
- [Persistencia](#persistencia)
- [Diagramas y diseño](#diagramas-y-diseño)
- [Novedades del Proyecto 3](#novedades-del-proyecto-3)
- [Autores](#autores)

## Características

- **Tres roles de usuario**: cliente, organizador y administrador, cada uno con su propio panel y flujo.
- **Eventos tipados**: musicales, deportivos, culturales y religiosos, cada uno con su propio porcentaje de ganancia.
- **Venues y localidades**: cada evento se asigna a un venue con múltiples localidades configurables (precio, capacidad, descuentos).
- **Catálogo de tiquetes**: tiquetes individuales, tiquetes múltiples y paquetes deluxe que agrupan tiquetes con mercancía adicional.
- **Carrito de compra y checkout** con historial de transacciones.
- **Marketplace**: los clientes pueden revender tiquetes mediante ofertas y contraofertas.
- **Sistema de peticiones**: los organizadores pueden solicitar la creación de venues o la cancelación de eventos, y los clientes pueden pedir reembolsos. Todas pasan por aprobación del administrador.
- **Reportes financieros**: ganancias por boletería, por organizador y por evento.
- **Generación de QR**: cada tiquete puede generar un código QR imprimible usando ZXing.
- **Interfaz gráfica (Swing)** con Look and Feel Nimbus, además de consolas de texto alternativas para cada rol.
- **Persistencia en JSON**: todos los datos se cargan y guardan desde archivos en `info/`, no requiere base de datos.

## Arquitectura

El código sigue una separación por capas con énfasis en polimorfismo y el patrón Manager para la lógica de negocio.

| Capa | Paquete | Responsabilidad |
|---|---|---|
| Modelo | `data`, `usuarios`, `eventos`, `tiquetes`, `peticiones`, `boleteria` | Entidades del dominio y clases abstractas base |
| Lógica | `managers` | Operaciones de negocio (compras, reembolsos, peticiones, venues, etc.) |
| Sesión | `sesion` | Estado de la sesión activa y carrito de compra |
| Persistencia | `persistencia` | Serialización y deserialización a JSON |
| Interfaz | `interfaz` | GUI en Swing, organizada por rol |
| Consola | `consolas` | Interfaces de texto alternativas por rol |
| Pruebas | `tests` | Tests unitarios con JUnit 5 |

**Jerarquías polimórficas principales:**

- `Usuario` → `Cliente`, `Organizador`, `Admin`
- `Evento` → `EventoMusical`, `EventoDeportivo`, `EventoCultural`, `EventoReligioso`
- `Tiquete` → `TiqueteIndividual`, `TiqueteMultiple`, `PaqueteDeluxe`
- `Peticion` → `PeticionCancelarEvento`, `PeticionNuevoVenue`, `PeticionReembolsoTiquete`, `PeticionRegistroMarketPlace`
- `Sesion` → `SesionCliente`, `SesionOrganizador`, `SesionAdmin`

El almacenamiento central está en `data/Datos.java`, que mantiene todas las entidades en `TreeMap` ordenados por identificador.

## Estructura del proyecto

```
Proyecto-3/
├── src/
│   ├── boleteria/       # Boletería y sistema financiero
│   ├── consolas/        # Interfaces de texto (Cliente, Admin, Organizador)
│   ├── data/            # Datos.java, contenedor central
│   ├── eventos/         # Evento, Venue, Localidad, Descuento
│   ├── interfaz/        # GUI Swing (clientStuff, adminStuff, organizadorStuff)
│   ├── managers/        # Lógica de negocio por dominio
│   ├── persistencia/    # Lectura/escritura JSON
│   ├── peticiones/      # Tipos de peticiones
│   ├── sesion/          # Sesiones y carrito
│   ├── tests/           # Tests JUnit 5
│   ├── tiquetes/        # Tiquetes, paquetes y artículos
│   └── usuarios/        # Usuario, Cliente, Organizador, Admin
├── lib/
│   ├── json-20250517.jar          # org.json
│   ├── core-3.5.3.jar             # ZXing core (QR)
│   └── javase-3.5.3.jar           # ZXing Java SE
├── info/                # Datos persistidos en JSON
├── docs/                # Enunciado, diseño y diagrama de clases
├── .classpath           # Configuración del classpath de Eclipse
└── .project             # Metadata del proyecto Eclipse
```

## Requisitos

- **JDK 21** o superior (configurado en `.classpath` como `JavaSE-21`).
- **Eclipse IDE** recomendado. El repo incluye `.project` y `.classpath` listos para importar.
- Las librerías externas ya están incluidas en `lib/`, no se necesita Maven ni Gradle.

## Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Proyectos-DPO/Proyecto-3.git
   ```
2. En Eclipse: **File → Import → Existing Projects into Workspace**, seleccionar la carpeta del repo.
3. Verificar que las tres librerías en `lib/` estén en el Build Path (deberían detectarse automáticamente).
4. Ejecutar la clase principal según la interfaz deseada:

   | Interfaz | Clase |
   |---|---|
   | GUI (recomendada) | `interfaz.VentanaInicio` |
   | Consola Cliente | `consolas.ConsolaCliente` |
   | Consola Organizador | `consolas.ConsolaOrganizador` |
   | Consola Admin | `consolas.ConsolaAdmin` |

Al iniciar, `Persistencia.cargarDatos()` lee los archivos JSON de `info/` y los carga en memoria.

## Usuarios de prueba

Los usuarios iniciales se definen en `info/usuarios.json`. Algunos disponibles para probar cada rol:

| Rol | Login | Contraseña |
|---|---|---|
| Cliente | `Charlie` | `2` |
| Organizador | `Martin` | `3` |
| Admin | `Nomas` | `1` |
| Admin | `Zukach` | `IsaacPapasoteSabroso123` |

El tipo de usuario se marca con un código en el JSON: `C` (Cliente), `CO` (Organizador) y `A` (Admin).

## Persistencia

No hay base de datos. Todo el estado vive en archivos JSON dentro de `info/`:

- `usuarios.json` — cuentas y su estado (saldo, tiquetes, notificaciones).
- `eventos.json` — eventos con sus venues, localidades y descuentos.
- `venues.json` — lugares donde se pueden realizar eventos.
- `tiquetes_i.json` — tiquetes individuales asociados a localidades.
- `tiquetes_m.json` — tiquetes múltiples (no atados a localidades).
- `paquetes.json` — paquetes deluxe con mercancía adicional.
- `peticiones.json` — peticiones pendientes y resueltas.
- `gananciasBoleteria.json` — acumulado de ganancias del sistema.
- `porcentajeEventos.json` — porcentaje de comisión por tipo de evento.

El guardado se hace a través de `persistencia.Persistencia`.

## Diagramas y diseño

- **Diagrama de clases completo**: [Google Drive](https://drive.google.com/file/d/1nTn5QisKpE_P3TjUpSgn0WyU69hOVOT4/view?usp=sharing)
- **Mockup de la interfaz (GUI)**: [Figma](https://www.figma.com/design/80RkHj7iolre6ByzspgeMx/Diagrama-DUI?node-id=0-1&p=f&t=cl1E6jFTMdcUEaq1-0)
- **Enunciado y PDF de diseño**: ver `docs/Proyecto 3.pdf` y `docs/Diseño UwU.pdf`.
- **Imagen local del diagrama**: `docs/Diagramota giganteeeeee.png`.

## Novedades del Proyecto 3

Respecto a las entregas anteriores, esta iteración agrega:

- **Generación de códigos QR** en `TiqueteManager.generarImagenTiquete()` usando la librería ZXing (ZebraCrossing). El QR se muestra en un `JFrame` durante 10 segundos.
- **Nuevos atributos en `Tiquete`**: `impreso: boolean` y `fechaDeExpedicion: LocalDate`, necesarios para la funcionalidad de impresión del QR.
- **Panels de la interfaz** implementados siguiendo el mockup de Figma.
- **Tests nuevos**: cobertura para `generarImagenTiquete()` y para `VenueManager` con todas sus funcionalidades.

## Autores

Proyecto desarrollado como entrega académica por el equipo de **Proyectos DPO**:

- Carlos David Huiza Moreno
- Martín Chicaiza
- poved

---

Curso: **Diseño y Patrones de Objetos (DPO)**.
