#!/bin/bash
# Setup selenium workdir
# Check your Chrome version an set here
chrome_version="113.0.5672.63"


python3 -m venv venv
source venv/bin/activate
pip install selenium
mkdir download
cd download
wget https://chromedriver.storage.googleapis.com/$chrome_version/chromedriver_linux64.zip
unzip chromedriver_linux64.zip
mv chromedriver ../venv/bin/
cd ..


