# 	₿ ORDER BOOK ₿:

<h4 align="center">The simple web application to get cumulative resizing of asks and bits.</h4>

<p align="center">
  <a href="#description">Description</a> •
  <a href="#structure">Structure</a> •
  <a href="#used-technologies">Used technologies</a> •
  <a href="#how-to-use">How To Use</a> •
</p>

## Description

This simple application outputs in log the difference in price levels (price) between the previous and current request.

## Structure
#### This web application structure is N-tier architecture model that including the following layers:
- Service
- Model
- DAO

## Used technologies

- Java 11
- Maven
- Spring (MVC, Security)

## How To Use

- Clone this project.
- In main method you can set the following parameters:
    - limit
    - timeout (value in milliseconds between getting new order book)
    - steps (value defines of quantity queries of getting new order books. 0 - endless cycle)
- Run app
- Enjoy
