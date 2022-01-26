# SAM Project for a Lambda Application which includes a Lambda Function and an API Gateway Endpoint

## Build
```bash
MAVEN_OPTS="-DskipTests=true" sam build --parameter-overrides WeatherFromIpFunctionArchitecture=arm64
```

## Run Function Locally
```bash
sam local invoke WeatherFromIpFunction --event WeatherFromIpFunction/src/test/resources/events/event.json --env-vars env.tpl.json
```

## Run Api and Function Locally
```bash
sam local start-api --env-vars .env.json
```

## Deploy
```bash
sam deploy --stack-name {stack-name} --s3-bucket {s3-bucket} --capabilities CAPABILITY_IAM --parameter-overrides WeatherApiKey={WeatherApiKey} WeatherFromIpFunctionArchitecture=arm64
```