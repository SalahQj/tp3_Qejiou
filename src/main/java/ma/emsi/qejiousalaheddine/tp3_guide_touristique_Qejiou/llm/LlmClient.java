package ma.emsi.qejiousalaheddine.tp3_guide_touristique_Qejiou.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
public class LlmClient implements Serializable {

    private final GuideTouristiqueAssistant assistant;

    public LlmClient() {
        String geminiApiKey = System.getenv("GEMINI_KEY");
        if (geminiApiKey == null || geminiApiKey.isEmpty()) {
            throw new RuntimeException("Erreur : GEMINI_KEY n'est pas définie.");
        }

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName("gemini-2.5-flash") // Votre modèle qui fonctionne
                .logRequests(true)
                .logResponses(true)
                .build();

        // Pas de mémoire nécessaire, chaque requête est indépendante
        this.assistant = AiServices.builder(GuideTouristiqueAssistant.class)
                .chatModel(model)
                .build();
    }

    // C'est la méthode que notre API REST va appeler
    public InfosTouristiques getInfos(String lieu) {
        return this.assistant.chat(lieu);
    }
}