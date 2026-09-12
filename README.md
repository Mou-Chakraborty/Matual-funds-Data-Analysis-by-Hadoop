Mutual Fund Data Analysis Using Hadoop

University Project for Big Data Analysis Laboratory

Implemented Apache Hadoop big data framework to analyze Mutual Funds data using HDFS and MapReduce.

Implemented Apache Pig data flow language built on top of Hadoop to perform Big Data Analysis using Pig Latin scripts.

Analyzed the Mutual Funds dataset to provide useful insights into fund houses, scheme categories, scheme types, and Net Asset Value (NAV).

Problem Statement :

Analyze the Mutual Funds dataset using Hadoop MapReduce and Apache Pig based on different column fields to provide various insights into mutual-fund data.

Summary :

The dataset used in this project is the Mutual Funds dataset containing historical mutual-fund scheme information.

Dataset :

The dataset is provided as a CSV file named:

https://www.kaggle.com/datasets/balajisr/indian-mutual-funds-dataset-2023

The dataset contains 29,033,647 records with the following 7 attributes:

1. Fund_House
2. Scheme_Type
3. Scheme_Category
4. Scheme_Code
5. Scheme_Name
6. Date
7. NAV

The dataset includes information such as mutual-fund companies, scheme types, scheme categories, scheme codes, scheme names, NAV observation dates, and Net Asset Values.

The dataset is stored in HDFS at:

/mutual_funds/Mutual_Funds.csv

Following MapReduce analysis is performed on the data-set :

1. Maximum NAV

2. Average NAV by Fund House

3. Scheme Category Count

Following Pig analysis is performed on the data-set :

1. Load and Display Mutual Fund Data

2. NAV Greater Than 1000

3. Average NAV by Scheme Category

4. Maximum NAV by Fund House

5. Scheme-Type Record Count

The project uses Apache Hadoop, HDFS, MapReduce, Apache Pig, Java, and Pig Latin for processing and analyzing the Mutual Funds dataset.

The reference repository is used as a guide for organizing Hadoop, MapReduce, Pig, and related Big Data analysis components.

About

Apache Hadoop, HDFS, MapReduce, Apache Pig, Java, Pig Latin
