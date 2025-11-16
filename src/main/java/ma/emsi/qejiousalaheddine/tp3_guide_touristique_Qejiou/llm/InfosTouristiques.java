package ma.emsi.qejiousalaheddine.tp3_guide_touristique_Qejiou.llm;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@JsonbPropertyOrder({ "ville_ou_pays", "endroits_a_visiter", "prix_moyen_repas" })
public record InfosTouristiques(
        String ville_ou_pays,
        List<String> endroits_a_visiter,
        String prix_moyen_repas
) {
}