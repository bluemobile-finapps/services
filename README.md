finapps-bm
==========

Finapps Bluemobile team

API description:

### Step 1. Hit 

  * For Sender:

        /send?lat=1&lon=1.5&name=sdafs&lastname=sdfsf&accn=345345&amount=123.4

        {"success":true,"data":{"name":"sdafs","lastName":"sdfsf","loc":{"latitude":1.0,"longitude":1.5},"ts":1351035531840,"acccount":null},"operationId":1351035531492}

  * For Receiver:

        /receive?lat=1&lon=1.5&name=sdafs&lastname=sdfsf&accn=34234234

        {"success":true,"data":{"name":"sdafs","lastName":"sdfsf","loc":{"latitude":1.0,"longitude":1.5},"ts":1351035531492,"amount":123.4,"acccount":"345345"},"operationId":1351035531492}

On error:

    {"success":false,"data":null,"operationId":0}

### Step 2. Confirm operation:

  * For Receiver:

        /accept/receive?idop=1351035531492 (idop is the operationId returned in the previous step)

        {"success":true,"data":{"name":"sdafs","lastName":"sdfsf","loc":{"latitude":1.0,"longitude":1.5},"ts":1351035531492,"amount":123.4,"acccount":"345345"},"operationId":1351035531492}

  * For Sender:
  
        /accept/send?idop=1351035531492

        {"success":true,"data":{"name":"sdafs","lastName":"sdfsf","loc":{"latitude":1.0,"longitude":1.5},"ts":1351035531740,"acccount":"34234234"},"operationId":1351035531492}
