
# Tarea Final - Diseño y Programación Orientada a Objetos (DPOO)

Este repositorio contiene el proyecto final de la asignatura Diseño y Programación Orientada a Objetos (DPOO) de la CUJAE. El objetivo principal del programa es la gestión de ofertas de empleo, implementado completamente en Java con Eclipse.

## Orientación
> Tema #7: Agencia empleadora

En una agencia empleadora se desea gestionar la bolsa de ubicación laboral. Dicha agencia se encarga de gestionar las ofertas y demandas de empleo de variadas empresas. De cada empresa se conoce: nombre, dirección, teléfono y sector en el que se encuentra ubicada (turismo, salud, educación, entre otros). Una empresa puede realizar solicitudes para tantos puestos de trabajo como necesite, así como solicitar la eliminación de puestos de trabajo que solicitó. De cada oferta se conoce: ID, rama a la que pertenece, salario del puesto y empresa a la que pertenece esta oferta.

Al llegar un candidato a empleado a la agencia se le recogen los siguientes datos: carné
de identidad, nombre, sexo, dirección, teléfono, nivel de escolaridad, especialidad, rama en la que desea trabajar (directivo, ingeniero, recursos humanos, entre otras) y años de experiencia en su área de especialidad. En dependencia de la rama en la que desee trabajar, se deben incluir otros datos (un candidato que desee trabajar en turismo debe presentar un certificado de idioma, un candidato que desee incorporarse a la plantilla de custodios debe presentar sus calificaciones en las pruebas de eficiencia física, así como su número de historia clínica). Una vez ingresado los datos al sistema, se comprueba si existen actualmente ofertas de empleo de la rama que le interesa. Se debe crear una funcionalidad en cada candidato, en la cual, dada una oferta, se sepa si el candidato puede o no aspirar a ese empleo.

De existir alguna oferta de empleo para el candidato, se programa una cita con la empresa interesada. El candidato puede asistir a todas las entrevistas que desee, pero solo puede asistir a una cita al día. De no existir un empleo que cumpla con los requerimientos del candidato, sus datos quedan almacenados en el sistema. Cuando una empresa realice una solicitud de empleo, los candidatos inscritos en el sistema deben ser revisados a ver si existe alguno con las cualidades necesarias para satisfacer dicha solicitud.

La agencia empleadora cuenta con un registro mensual de la planificación de entrevista, donde se tiene por día, para cada empleo que solicita una empresa, si hay planificada una entrevista o no.

## Diagrama
![Diagrama UML](./docs/LogicUML.webp)
