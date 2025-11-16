package ma.emsi.qejiousalaheddine.tp3_guide_touristique_Qejiou.resources;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.qejiousalaheddine.tp3_guide_touristique_Qejiou.llm.LlmClient;
import ma.emsi.qejiousalaheddine.tp3_guide_touristique_Qejiou.llm.InfosTouristiques;

// ▼▼▼ 4. AJOUT DES IMPORTS POUR LE BONUS ▼▼▼
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.DefaultValue;

@Path("/guide")
@RequestScoped
public class GuideTouristiqueResource {

    @Inject
    private LlmClient llmClient;

    @GET
    @Path("/lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    // ▼▼▼ 5. MODIFICATION DE LA MÉTHODE ▼▼▼
    public Response guideVilleOuPays(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nb) { // <-- Ajout du QueryParam "nb", avec 2 comme défaut

        try {
            // 6. Appeler le LLM avec le nombre d'endroits
            InfosTouristiques reponse = llmClient.getInfos(villeOuPays, nb);

            // 7. Construire la réponse
            Response.ResponseBuilder responseBuilder = Response.ok(reponse);

            // 8. AJOUT DES EN-TÊTES (Bonus optionnel HTML/CORS)
            responseBuilder.header("Access-Control-Allow-Origin", "*");
            responseBuilder.header("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            responseBuilder.header("Pragma", "no-cache");
            responseBuilder.header("Expires", "0");

            return responseBuilder.build();

        } catch (Exception e) {
            return Response.serverError()
                    .entity("Erreur interne du serveur: " + e.getMessage())
                    .build();
        }
    }
}