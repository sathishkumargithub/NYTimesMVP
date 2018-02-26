# NYTimes Application using MVVM Design pattern 

## Introduction

NYtimes application shows a list of NYTimes most viewed articles. Tapping on the article in a list show a detailed view of the particular 
article.  
* A main activity that handles navigation between articlelistfragment and articledetailfragment.
* A articlelistfragment to display the list of products.
* A articledetailfragment to display a product review.

## Model-View-Presenter (MVP)
### Presenter
The presenter is responsible to act as the middle man between view and model. It retrieves data from the model and returns it formatted to the view. 
### View
The view, usually implemented by an Activity, will contain a reference to the presenter. The only thing that the view will do is calling a method from the presenter every time there is an interface action.
### Model
In an application with a good layered architecture, this model would only be the gateway to the domain layer or business logic. Model is the provider of the data we want to display in the view.

 __
 ## License 
 Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
