package com.tddjava.ch07booksstore;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.UUIDExceptionWrapper;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther ouxx
 * @create 2018/6/6 21:52
 */
public class Runner extends JUnitStories {
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(getReporter())
                .useStoryLoader(new LoadFromURL());
    }

    private StoryReporterBuilder getReporter() {
        return new StoryReporterBuilder()
                .withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName())
                .withDefaultFormats()
                .withFormats(Format.CONSOLE, Format.HTML);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new Steps() );
    }

    @Override
    protected List<String> storyPaths() {
//        String path = "/stories/**/*.story";
        String path = "stories/**/*.story";
        return new StoryFinder().findPaths(
                CodeLocations.codeLocationFromPath("").getFile(),
                Collections.singletonList(path),
                new ArrayList<String>(),
                "file:"
        );
    }

    @Override
    @Test
    public void run() throws Throwable {
        Embedder embedder  = configuredEmbedder();
        try {
            embedder.runStoriesAsPaths(storyPaths());
        } finally {
            embedder.generateCrossReference();
        }
    }


}
