# Cours5-Fragment-Communication
Communication entre fragment a l'aide d'une interface


- 2 Fragments d'une meme activity "ne se connaissent pas"
- Pour communiquer le Frgament A doit donc envoyer l'information a l'activity qui la renvoie au fragment B

Pour communiquer d'une activity a un fragment:

- On recupere l'instance du fragment ou par son tag via le FragmentManager ou tout simplement conserve dans une variable 
- On appel une fonction publique du Fragment comme avec n'importe quel objet

Pour communiquer d'un Fragment a une Activity:

- On cree une interface
- On declare dans l'interface les fonctiosn que l'on veut pouvoire appeler dans l'activity depuis le fragment
- On implement l'interface dans l'activity et donc les fonctions de l'interface egalement
- Dans le Fragment on override la fonction onAttach(Context context) (evenement lyfeCycle du fragment correspondant a son attach a l'activity qui le supporte)
- Le context recu en parametre n'est autre que l'instance de l'activitu qui supporte le fragment
- Dans onAttach on verifie que le context est bien instanceOf notre interface , soit donc que l'Activity est bien egalement du type de notre interface soit donc que l'activity implement bien notre interface
- Si oui alors creer une variable mListener de type notre interface et l'initialisons avec le context caste en notre interface
- Ce casting consiste a prendre de l'instance de l'activity (context) uniquement sa part qui est d'implementer l'inetrface et donc d'avoir les fonctions que notre inetrface lui a fait implementer
- Ayant desormais l'instance de la part de l'activity qui a les fonctiosn implementees de notre interface 
- On peut desormais appeler ces fonctions se trouvant dans l'activity via la variable mListener
- Ainsi quand j'appel la fonction mListener.onCounterChange(counter) c'est bien la fonction de l'activity que j'appel depuis le fragment (en lui passant en plus le parametre counter)

- Si j'appel dans la fonction onCounterChange de l'activity , la fonction public du Fragment B je peux donc par exemple realiser le circuit:
- Fragment A : mListener.onCounterChange(counter) 
- Ce qui appel la fonction onCounterChange(int counter) dans l'activity
- Qui appel la fonction publique fragment.updateView(counter)  
- Dans fragment B updateView(int counter) pourra par exemple afficher la valeur du compteur dans un textview


ressources: slide 1.2
https://drive.google.com/drive/folders/1MRqvBGEDtNtpDyKd8sulMJreFCz1JxgC?usp=sharing
