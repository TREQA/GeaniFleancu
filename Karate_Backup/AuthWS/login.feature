Feature: Login

  Background: set headers

    Given url Origin
    And header Accept = "application/json"
    And header Content-Type = "application/json"
    And header Host = Host
    And header Origin = Origin

    # set ws path
    And path "services/auth/v1/login"

    #set username and password
    Given def authUsername = "vn-84-vly"
    Given def authPassword = "test1234"
    Then print "####### " + "Starting login with user: " + authUsername + " and password: " + authPassword

  Scenario: login

    #request body
    And request {"password":"#(authPassword)", "username":"#(authUsername)"}

    # POST
    When method post
    Then status 200

    #extract auth token
    And def authToken = responseCookies.Authorization
    Then print "####### " + "Login success. Auth token is = " + authToken.value