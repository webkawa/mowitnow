# mowitnow
Un simulateur de tondeuses à gazon.

Usage :

    java com.mowitnow.program.Runner [-verbose] [-file|-folder|-raw] input

Avec :

    -verbose    Affichage d'information complémentaires au résultat
    -file       Lecture d'un fichier (par défaut)
    -folder     Lecture d'un dossier
    -raw        Lecture d'un texte brut
    input       La chaine d'entrée : fichier, dossier, ou valeur brute dépendamment du mode de fonctionnement.
    
Remarques :

    1. La méthode employée pour l'accès aux fichiers convient à des exemples de taille restreinte mais pourrait
       lever des problèmes de performance.
    2. En mode dossier, la lecture s'arrete dès qu'une erreur se produit. Il aurait pu etre choisi de tolérer
       une erreur ponctuelle et juste passer au fichier suivant.
    3. En cas d'erreur, la stack est volontairement affichée dans la console. Ce mode de fonctionnement peut lever
       des problématiques de sécurité lors d'un usage en production (notamment dans un contexte web).
    4. Une gestion des logs aurait été bienvenue mais je manque de temps :-)
    5. Des tests unitaires basés sur JUnit peuvent etre trouvés dans le paquet com.mowitnow.test
    6. Les fichiers passés en paramètre doivent etre encodés en UTF-8.
    7. Les librairies sont enregistrées directement dans le dossier "./lib". Dans un contexte projet plus large,
       l'utilisation d'un gestionnaire type Maven aurait été souhaitable.
    8. Le comportement souhaité en cas de collision entre deux tondeuses n'étant pas spécifié dans la spécification,
       j'ai choisi de projeter une exception le cas échéant.