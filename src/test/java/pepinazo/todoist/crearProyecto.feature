Feature: Creación de proyectos en todoist

  Scenario Outline: Crear proyecto nuevo desde el icono de mas
    Given hice login con <user> y <password>
    When Agrego un proyecto nuevo desde "el icono de +"
    Then El nuevo proyecto debe estar listado al final


    Examples:
    |user                     | password|
    |jomarnavarron@gmail.com  | Test@1234|

  Scenario: Crear proyecto desde la liga de 'Add project'
    Given Yo ingrese a la aplicacion de todoist
    When Agrego un proyecto nuevo desde "la liga de New Project"
    Then El nuevo proyecto debe estar listado al final

  #Scenario 3: Crear proyecto desde la liga de 'Create Project Above'

# Scenario 4: Crear proyecto desde la liga de 'Create Project Below'

 # Scenario 5: Create proyecto desde una tarea

  Scenario: X
    Given I have 5 marbles
    When I give away 3
    Then I have 2 marbles left