# Micronaut OCI Email Delivery Demo

## Send

```shell
$ curl -i localhost:8080/email
```

## Send with Attachment

```shell
$ curl -X POST \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/Users/trsharp/Pictures/demo/apple.jpg" \
  localhost:8080/email/attachment
```

## Send with Template

```shell
$ curl -i localhost:8080/email/template/Todd%20Sharp
```

run 
    -DSMTP_HOST=smtp.us-ashburn-1.oraclecloud.com 
    -DSMTP_PORT=587 
    -DSMTP_USER=ocid1.user.oc1..aaaaaaaaiwjvbdfijcpshflmg7su7oms75t7u2dcug4t4vq4c6wofhm3axlq@ocid1.tenancy.oc1..aaaaaaaa46dtneevd6c2cujmryjcrqmuhkwv2khh73gcgqwratpqqsa2hrtq.hz.com 
    -DSMTP_PASSWORD=EWvEv0Y6-Ajlq77{_sYy 
    -DEMAIL_TO=cfsilence@gmail.com 
    -DEMAIL_FROM=toddraymondsharp@gmail.com