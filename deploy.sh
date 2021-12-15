#!/bin/bash
set -eo pipefail
# Checking S3 bucket if present to upload code files
if [ -e bucket-name.txt ]
then
    echo "s3 bucket found: " $(cat bucket-name.txt)
else
  {
    BUCKET_ID=$(dd if=/dev/random bs=8 count=1 2>/dev/null | od -An -tx1 | tr -d ' \t\n')
    BUCKET_NAME=lambda-artifacts-$BUCKET_ID
    echo $BUCKET_NAME > bucket-name.txt
    aws s3 mb s3://$BUCKET_NAME
    echo "created new s3 bucket: " $BUCKET_NAME
  }
fi

# Performing cloudformation packaging and deployment
ARTIFACT_BUCKET=$(cat bucket-name.txt)
echo -e "ARTIFACT_BUCKET: " $ARTIFACT_BUCKET "\n"
TEMPLATE=template.yml
echo -e "TEMPLATE file name: " $TEMPLATE "\n"

echo -e "-------- Aws cloudformation packaging STARTED------- \n"
aws cloudformation package --template-file $TEMPLATE --s3-bucket $ARTIFACT_BUCKET --output-template-file out.yml
echo -e "\n-------- Aws cloudformation packaging ENDED ------- \n"
echo -e "-------- Aws cloudformation Deployment STARTED -------\n"
aws cloudformation deploy --template-file out.yml --stack-name Lambda --capabilities CAPABILITY_NAMED_IAM
echo -e "\n -------- Aws cloudformation Deployment ENDED -------"

sleep 5s
