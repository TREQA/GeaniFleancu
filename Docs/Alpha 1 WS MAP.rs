[TIMESTAMP]

GET http://ro-test.rbro.rbg.cc/services/utils-service/v1/now

[LOCATOR]

GET http://ro-test.rbro.rbg.cc/services/locator/v1/branches

[FX RATES]

GET http://ro-test.rbro.rbg.cc/services/fx-rates-ws/v1/list

GET http://ro-test.rbro.rbg.cc/services/fx-rates-ws/v1/sell/RON/GBP

GET http://ro-test.rbro.rbg.cc/services/fx-rates-ws/v1/buy/RON/JPY

[INTEREST RATES]

GET http://ro-test.rbro.rbg.cc/services/interest-rates/v1/interest-rates

[LOGIN]

POST http://ro-test.rbro.rbg.cc/services/auth/v1/login

POST data:
{"password":"test1234","username":"vn-84-vly"}

[DASHBOARD]

GET http://ro-test.rbro.rbg.cc/services/marketing-service/v1/isEligible

GET http://ro-test.rbro.rbg.cc/services/accounts-ws/v1/accounts/listHolds

GET http://ro-test.rbro.rbg.cc/services/accounts-ws/v1/accounts/list

[CARDS]

GET http://ro-test.rbro.rbg.cc/services/cards-ws/v2/cards?page=0&size=10&status=All&clientType=PF&sourceApplication=IBK

[ACCOUNT OVERVIEW]

POST http://ro-test.rbro.rbg.cc/services/transactions-service/v1/transactions
{"accountId":"0005161502","accountType":"20","size":10,"transactionStatusTypes":["SUCCESS"]}

POST http://ro-test.rbro.rbg.cc/services/transactions-service/v1/transactions
{"accountId":"0005161502","accountType":"20","size":100,"transactionStatusTypes":["HOLD"]}
