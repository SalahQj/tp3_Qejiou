package ma.emsi.qejiousalaheddine.tp3_guide_touristique_Qejiou.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristiqueAssistant {

    @SystemMessage("""
        Tu es un guide touristique expert. Réponds UNIQUEMENT avec un objet JSON
        valide, basé sur le schéma demandé.
        N'ajoute AUCUN texte avant ou après le JSON, et n'utilise PAS de Markdown.
        Le JSON doit avoir exactement 3 clés :
        1. 'ville_ou_pays' (le nom du lieu demandé)
        2. 'endroits_a_visiter' (une liste de noms de lieux)
        3. 'prix_moyen_repas' (une chaîne de caractères avec le prix ET la devise, ex: "20 EUR")
        """)
    // ▼▼▼ MODIFICATION ICI ▼▼▼
    @UserMessage("""
        Donne-moi les informations touristiques pour {{lieu}}.
        La liste 'endroits_a_visiter' doit contenir exactement {{nb}} endroits principaux.
        """)
    InfosTouristiques chat(@V("lieu") String lieu, @V("nb") int nb); // <-- 1. Ajout de 'nb'
}