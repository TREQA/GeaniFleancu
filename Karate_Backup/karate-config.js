function() {
 /* var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'test';
  }*/
  var env = 'test'

  var config = { // base config
    env: env,
    Host: 'ro-test.rbro.rbg.cc',
    Origin: 'http://ro-test.rbro.rbg.cc',
    url: 'ro-test.rbro.rbg.cc'
  };
  if (env == 'dev') {
    config.Host = 'ro-dev.rbro.rbg.cc';
    config.Origin = 'http://ro-dev.rbro.rbg.cc';
    config.url = 'ro-test.rbro.rbg.cc';
  }
  if (env == 'pre') {
     config.Host = 'ro-pre.rbro.rbg.cc';
     config.Origin = 'http://ro-pre.rbro.rbg.cc';
     config.url = 'ro-test.rbro.rbg.cc';
  }
  else if (env == 'test') {
     config.Host = 'ro-test.rbro.rbg.cc';
     config.Origin = 'http://ro-test.rbro.rbg.cc';
     config.url = 'ro-test.rbro.rbg.cc';
  }
  // don't waste time waiting for a connection or if servers don't respond within 15 seconds
  karate.configure('connectTimeout', 15000);
  karate.configure('readTimeout', 15000);
  return config;
}