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

@Path("/guide") // URL: /api/guide
@RequestScoped // Un nouveau bean pour chaque requête HTTP
public class GuideTouristiqueResource {

    @Inject
    private LlmClient llmClient; // Injection du client LangChain4j

    @GET
    @Path("/lieu/{ville_ou_pays}") // URL: /api/guide/lieu/Paris
    @Produces(MediaType.APPLICATION_JSON) // Dit à JAX-RS de convertir la réponse en JSON
    public Response guideVilleOuPays(@PathParam("ville_ou_pays") String villeOuPays) {

        try {
            // 1. Appeler le LLM
            InfosTouristiques reponse = llmClient.getInfos(villeOuPays);

            // 2. Retourner la réponse
            // JAX-RS va automatiquement convertir l'objet 'reponse' en JSON
            return Response.ok(reponse).build();

        } catch (Exception e) {
            // 3. Gérer les erreurs (ex: clé API)
            return Response.serverError()
                    .entity("Erreur interne du serveur: " + e.getMessage())
                    .build();
        }
    }
}