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


Pour sauvegarder des donnees en cas de destruction de l'activity ou du fragment et recreation automatique par Android de ceux ci (cas de rotation)

- on utilise onSaveInstanceState pour inserer nos donnees dans le Bundle recu en parametre
- Bundle que l'on recupere dans les differentes fonctions de creation du lifeCycle

Pour appeler une fonction se trouvant dans un Fragment ou une activity (et transmetre des informations) directement de nimporte quel endroit du projet

- On utilise eventBus https://github.com/greenrobot/EventBus
- On cree un evenement qui est une simple class
- On post un evenment de la ou on le souhaite en passant en parametre une instance de la class cree pour cet evenement precedement
- Dans l'activity ou le fragment on dit que l'on est a l'ecoute des evenemenets eventbus en register dasn onStart (pas oublie d'arreter l'ecoute dasn onStop avec unregister)
- On annote la fonction que l'poon souhaite appele quand cet evenement est emis avec @Subscribe
- On declare que cette fonction recoit en parametre un objet du type de l'evenment dulequel on souhaite etre a l'ecoute

Pour communiquer entre 2 fragments

- On utilise l'interface quand il est vraiment indispensable que l'activity soit a l'ecoute de l'evenement emis par le fragment 
- Et EventBus dasn les autres cas


ressources: slide 1.2
https://drive.google.com/drive/folders/1MRqvBGEDtNtpDyKd8sulMJreFCz1JxgC?usp=sharing
