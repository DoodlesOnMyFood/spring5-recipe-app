package guru.springframework.spring5recipeapp.converters;


import guru.springframework.spring5recipeapp.commands.NotesCommand;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if(notesCommand == null) return null;
        final Notes notes = new Notes();
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        notes.setId(notesCommand.getId());
        return notes;
    }
}
