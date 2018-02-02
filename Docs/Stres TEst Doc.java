http://jira.rbro.rbg.cc/browse/ARO-4306
atd:
  api.version: 1.5.0
  url: "https://10.241.127.49"
  username: "robotie"
  password: "1 fruit MUSIC vis@"
fileupload.url: "${atd.url}/php/fileupload.php"
brief.status.url: "${atd.url}/php/samplestatus.php"
  session.url: "${atd.url}/php/session.php"
 UploadRequestData uploadData = new UploadRequestData();
    uploadData.setSubmitType(submitType);
    uploadData.setUrl(url);
    uploadData.setAnalyzeAgain(1);
    uploadData.setxMode(0);

    map.add("data", new UploadRequest(uploadData));

    HttpHeaders httpHeaders = requestHttpHeaders(session, userId);
    httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

    HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(map, httpHeaders);
    ResponseEntity<String> response = RestUtils.restTemplateNoSSlCheck()
        .exchange(atdFileUpload, HttpMethod.POST, entity, String.class);




        cm9ib3RpZToxIGZydWl0IE1VU0lDIHZpc0A=



        public static void main(String[] args) {

           File f =  new File("C:/Users/gfleancu/Google Drive/Jmeter/Tests/jpgTest2.jpg");
             String encodstring = encodeFileToBase64Binary(f);
             System.out.println(encodstring);
       }

       private static String encodeFileToBase64Binary(File file){
            String encodedfile = null;
            try {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStreamReader.read(bytes);
                encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return encodedfile;
        }
