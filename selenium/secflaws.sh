#!/bin/bash

source venv/bin/activate

python secflaws.py >> secflaws.log 2>&1
