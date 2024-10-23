package com.ashcoopeer.discord.services;

import com.ashcoopeer.discord.dao.CommandParameterRepository;
import com.ashcoopeer.discord.dao.CommandRepository;
import com.ashcoopeer.discord.dto.CommandDto;
import com.ashcoopeer.discord.dto.CommandParameterDto;
import com.ashcoopeer.discord.model.Command;
import com.ashcoopeer.discord.model.CommandParameter;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommandService extends CrudService<Command, CommandDto, Long> {

    private CommandParameterRepository commandParameterRepository;

    public CommandService(CommandRepository commandRepository, CommandParameterRepository commandParameterRepository) {
        super(commandRepository);
        this.commandParameterRepository = commandParameterRepository;
    }

    @Override
    public Command toEntity(CommandDto commandDto) {
        Command entity = new Command();
        BeanUtils.copyProperties(commandDto, entity);
        return entity;
    }

    @Override
    public CommandDto toDto(Command entity) {
        CommandDto commandDto = new CommandDto();
        BeanUtils.copyProperties(entity, commandDto);
        return commandDto;
    }

    public CommandDto findByName(String commandName) {
        Optional<Command> commandOptional = ((CommandRepository) this.repository).findByName(commandName);
        CommandDto commandDto;
        if (commandOptional.isPresent()) {
            List<CommandParameterDto> commandParameterDtoList = new ArrayList<>();
            List<CommandParameter> parameterList = commandParameterRepository.findByCommandId(commandOptional.get().getId());

            if (parameterList != null && !parameterList.isEmpty()) {
                parameterList.forEach(commandParameter -> {
                    commandParameterDtoList.add(CommandParameterDto.builder()
                            .name(commandParameter.getName())
                            .regex(commandParameter.getRegex())
                            .order(commandParameter.getOrder())
                            .build());
                });
            }



            commandDto = toDto(commandOptional.get());
            commandDto.setCommandParameterDtoList(commandParameterDtoList);

            return commandDto;
        }

        return null;
    }
}
