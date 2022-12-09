<?php

include "fonctions.php";
if(isset($_REQUEST["operation"])){
	if($_REQUEST["operation"] == "enreg"){
            $donnee = json_decode($_REQUEST["lesdonnees"]);
            $datemesure = $donnee[0];
            $poids = $donnee[1];
            $taille = $donnee[2];
            $age = $donnee[3];
            $sexe = $donnee[4];
            
            try {
                print("enreg%");
                $cnx = connexionPDO();
                $larequete = "insert into profil (datemesure, poids, taille, age, sexe)";
                $larequete .= " values (\"$datemesure\", $poids, $taille, $age, $sexe)";
                print($larequete);
                $req = $cnx->prepare($larequete);
                $req->execute();
            }
            catch (Exception $e){
                print("Erreur ! %". $e->getMessage());
                die();
            }
        }
        if($_REQUEST["operation"] == "dernier"){
            print("dernier%");
            $cnx = connexionPDO();
            $req = $cnx->prepare("select * from profil order by datemesure desc");
            $req->execute();
            //s'il y a un profil, récuperation du premier
            if($ligne = $req->fetch(PDO::FETCH_ASSOC)){
                print(json_encode($ligne));
            }
        }
        
        if($_REQUEST["operation"] == "tous"){
            try{
                print("tous%");
                $cnx = connexionPDO();
                $req = $cnx->prepare("select * from profil order by datemesure asc");
                $req->execute();
                $resultat = [];
                while($ligne = $req->fetch(PDO::FETCH_ASSOC)){
                    $resultat[] = $ligne;
                }
                print(json_encode($resultat));
            } catch (Exception $ex) {
                print("Erreur !%".$e->getMessage());
                die();
            }     
        }
        
        if($_REQUEST["operation"] == "supprimer"){
            try{
                print("supprimer%");
                $donnee = json_decode($_REQUEST["lesdonnees"]);
                $profilSuppr = $donnee[0];
                print($profilSuppr);
                $cnx = connexionPDO();
                $req = $cnx->prepare("delete from profil where datemesure= \"$profilSuppr\"");
                $req->execute();
            } catch (Exception $ex) {
                print("Erreur !%".$e->getMessage());
                die();
            }
        }
}
?>