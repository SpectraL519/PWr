package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;



public class WordCensor extends SocialChannelDecorator {
    private final String[] censored_words;
    // SocialChanel delegate - protected field in class SocialChannelDecorator

    public WordCensor (String[] censored_words){
        this.censored_words = censored_words;
    }

    public WordCensor (String[] censored_words, SocialChannel channel){
        this.censored_words = censored_words;
        this.delegate = channel;
    }


    @Override
    public void deliverMessage (String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(message);
        for (String word : censored_words) {
            int i = builder.indexOf(word);
            while (i != -1) {
                builder.delete(i, i + word.length());
                String repeated = new String(new char[word.length()]).replace("\0", "*");
                builder.insert(i, repeated);
                i = builder.indexOf(word);
            }
        }

        if (delegate == null)   return;
        delegate.deliverMessage(builder.toString());
    }
}
