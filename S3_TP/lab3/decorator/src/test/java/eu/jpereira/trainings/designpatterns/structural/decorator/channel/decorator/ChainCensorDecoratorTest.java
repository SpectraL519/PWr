package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest{

    @Test
    public void testChainThreeDecorators() {
        // Create the builder
		SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

		// create a spy social channel
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

		// Chain decorators
		SocialChannel channel = builder.
				with(new MessageTruncator(10)).
				with(new URLAppender("http://jpereira.eu")).
                with(new WordCensor(new String[]{"this"})).
				getDecoratedChannel(props);

		channel.deliverMessage("this is a message");
		// Spy channel. Should get the one created before
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		assertEquals("**** is... http://jpereira.eu", spyChannel.lastMessagePublished());
	}

    @Test
    public void testChainThreeDecoratorsWithoutBuilder() {

        TestSpySocialChannel channel = new TestSpySocialChannel();

        SocialChannel urlAppenderChannel = new URLAppender("http://jpereira.eu", channel);

        //Now create a truncator
        SocialChannel messageTruncatorChannel = new MessageTruncator(10, urlAppenderChannel);
        SocialChannel wordCensorChannel = new WordCensor(new String[]{"this"}, messageTruncatorChannel);

        wordCensorChannel.deliverMessage("this is a message");
        // Spy channel. Should get the one created before
        assertEquals("**** is... http://jpereira.eu", channel.lastMessagePublished());
    }
    
    @Test
    public void testOtherChainThreeDecorators() {
        // Create the builder
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        // create a spy social channel
        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

        // Chain decorators
        SocialChannel channel = builder.
                with(new URLAppender("http://jpereira.eu")).
                andWith(new MessageTruncator(30)). // Why is there an 'andWith' method - it's useless
                with(new WordCensor(new String[]{"is", "age"})).
                getDecoratedChannel(props);

        channel.deliverMessage("this is a message");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("th** ** a mess*** http://jp...", spyChannel.lastMessagePublished());
    }
    
}
