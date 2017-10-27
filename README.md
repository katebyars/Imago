# _IMAGO_

#### _An app for Instagram influencers to find hireable Unsplash photographers. _

#### By _**Kate Byars**_

## Description

_Today, photographers abound. That special 'instagrammable' style, however, is not so plentiful, epecially as you peruse the studio listings in your town. If you want a portrait that is different than the norm, or simply want to get those social media worthy shots, use this app! It uses the Unsplash API to find photographers that live in or are shooting in your town.This is an app created for the Introduction to Programming course at Epicodus, Portland. This app is currently under construction and does not represent the MVP._

_Current Project Task List_

| Task      | Status    |
| ------------- | ------------- |
| Change small sized images to large sized | in progress |
| Heart button saves to Firebase |in progress|
| Display Firebase favorites via RecyclerAdapater on a favorites pages |in progress|
| Display recently searched items in a a section of MainActivity layout as "Recently Searched" list/fragment |in progress|
| Create dialogs for login status |in progress|
| Update UI to flat design | in progress |

_The home page includes a search box and submit button, along with a bottom nav bar._
![Description](https://github.com/katebyars/Imago/blob/master/app/src/main/res/drawable/a1.png)
_Search results appear in a separate activity with description and image thumb nail. I will add logic later to handle 'null' cases._
![Description](https://github.com/katebyars/Imago/blob/master/app/src/main/res/drawable/a2.png)
_Each image has a detail fragment. I will update this fragment with logic that corresponds to more image details_
![Description](https://github.com/katebyars/Imago/blob/master/app/src/main/res/drawable/a3.png)
_Once the pizza is added to the order, the user can see that pizza's details in the Your Order area._


## Specs

* _Calls to the UnSplash API return photo or user results based on any query value._
  * _Examples : Cakes returns photos of cakes._
* _Each photo has a detail page._
  * _Example : A picture of a cake is clickable and brings you to a page of details on the image and the photographer._
* _The photographers are searchable by location_
  * _Example : Alaska returns 1,000 photographers by profile location_
* _The images are searchable by location._
  * _Example : Alaska returns 10,0000 images shot in locations in Alaska_
* _Photographers contact details are included in the search results._
  * _Example : Alaska returns 10,0000 images shot in locations in Alaska and the first result is Anne Spitzen who lives in Anchorage._


## Known Bugs

_{There are no known bugs in this program but it is currently under development. The most recent branch is called UI.}_

## Support and contact details

_{Please let the author know if you have questions. Kate Byars katebyars5@gmail.com}_

## Technologies Used

_{Java, Android}_

### License

*{MIT}*

Copyright (c) 2017 **_{Kate Byars}_**